import pandas as pd 
import numpy as np 
import statsmodels as sm
import scipy.stats as scs
import os
import sys


def CalculatePortfolio(riskFree):
    #get StockPrices and ticker_list
    abs_path = os.path.abspath('.')
    #print(abs_path)
    stock_data = pd.read_csv(abs_path + "/stockCloseData.txt")


    number = 1000

    #get StockReturns
    StockPrices = pd.DataFrame(stock_data)
    StockReturns = StockPrices.pct_change().dropna()
    #print(StockReturns)
    stock_return = StockReturns.copy()

    ticker_list = StockPrices.columns
    StockNumber = len(ticker_list)

    #print(stock_return)
    # 计算协方差矩阵
    cov_mat = stock_return.cov()
    # 年化协方差矩阵
    cov_mat_annual = cov_mat * 252
    # 输出协方差矩阵
    #print(cov_mat_annual)

    
    # 设置空的numpy数组，用于存储每次模拟得到的权重、收益率和标准差
    random_p = np.empty([number, StockNumber + 2])
   
    # 设置随机数种子，这里是为了结果可重复
    np.random.seed(StockNumber)



    
    #循环模拟10000次随机的投资组合
    for i in range(number):
        #生成5个随机数，并归一化，得到一组随机的权重数据
        random5=np.random.random(StockNumber)
        random_weight=random5/np.sum(random5)
    
        #计算年平均收益率
        mean_return=stock_return.mul(random_weight,axis=1).sum(axis=1).mean()
        annual_return=(1+mean_return)**252-1
    
        #计算年化标准差，也成为波动率
        random_volatility=np.sqrt(np.dot(random_weight.T,np.dot(cov_mat_annual,random_weight)))
    
        #将上面生成的权重，和计算得到的收益率、标准差存入数组random_p中
        random_p[i][:StockNumber]=random_weight
        random_p[i][StockNumber]=annual_return
        random_p[i][(StockNumber + 1)]=random_volatility
    
    #将Numpy数组转化为DataF数据框
    RandomPortfolios=pd.DataFrame(random_p)
    #设置数据框RandomPortfolios每一列的名称
    RandomPortfolios.columns=[ticker +'_weight' for ticker in ticker_list]+['Returns','Volatility']
    
    # 设置无风险回报率为0
    risk_free = riskFree
    #risk_free = 0.3
    # 计算每项资产的夏普比率
    RandomPortfolios['Sharpe'] = (RandomPortfolios.Returns - risk_free) / RandomPortfolios.Volatility
    max_index = RandomPortfolios.Sharpe.idxmax()

    print( RandomPortfolios.loc[max_index])
    #return RandomPortfolios.loc[max_index]
    # min_index = RandomPortfolios.Sharpe.idxmin()
    # print (RandomPortfolios.loc[min_index])

if __name__ == "__main__":
    a = []
    for i in range(1, len(sys.argv)):
        a.append((float(sys.argv[i])))
    CalculatePortfolio(a[0])