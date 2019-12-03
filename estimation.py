import pandas as pd
import os
import numpy as np
import glob
import codecs
import statsmodels.api as sm
from statsmodels import regression
import sys
# abs_path = os.path.abspath('.')
# print(abs_path)
# data1 = pd.read_csv(abs_path + "/AA.txt")
#
# data1['date'] = pd.to_datetime(data1['Date'],format='%Y%m%d', errors='ignore')
# #print(data1)
#
# mask = (data1['date'] > '2003-03-03') & (data1['date'] < '2004-03-03')
# d = data1.loc[mask]
# f = d['Close']






# data1 = pd.read_csv('/Users/yongzhaohuang/Documents/python_project/teach/comp5513/assign/tdata' + '/' + files[0])
# data1['date'] = pd.to_datetime(data1['Date'],format='%Y%m%d', errors='ignore')
# mask = (data1['date'] > '2003-03-03') & (data1['date'] < '2004-03-03')
# d = data1.loc[mask]
# f = d['Close']
# #print(f)
#
#
# data2 = pd.read_csv('/Users/yongzhaohuang/Documents/python_project/teach/comp5513/assign/tdata' + '/' + files[1])
# data2['date'] = pd.to_datetime(data2['Date'],format='%Y%m%d', errors='ignore')
# mask = (data2['date'] > '2003-03-03') & (data2['date'] < '2004-03-03')
# g = data2.loc[mask]
# l = g['Close']
# #print(l)

def calculate_estimate(df1,df2):

    return_target = df1.pct_change()[1:]
    return_all = df2.pct_change()[1:]



    def linreg(x,y):
        x = sm.add_constant(x)
        model = regression.linear_model.OLS(y,x).fit()
        #print(model)
        x = x[:,1]
        return model.params[0], model.params[1]

    #print("hello world")
    alpha, beta = linreg(return_all.values, return_target.values)
    #print("alpha:" + str(alpha))
    print(str(beta.round(4)))

def get_format(dirName, startDate, endDate, targetName):
    #get sum of all stock close values
    os.chdir(dirName)
    files = glob.glob('*.txt')

    #target stock
    target_name = targetName
    target = pd.DataFrame()

    #define file path
    dir_name = dirName
    start_date = startDate
    end_date = endDate

    result = pd.DataFrame()
    data = pd.read_csv(dirName + '/' + files[0])
    data['date'] = pd.to_datetime(data['Date'], format='%Y%m%d', errors='ignore')
    #mask = (data['date'] > '2004-03-03') & (data['date'] < '2005-03-03')
    mask = (data['date'] > start_date) & (data['date'] < end_date)
    d = data.loc[mask]
    f = d['Close']
    k = f.reset_index(drop=True)
    if ((target_name + ".txt") == files[0]):
        target = k
    result = k
    for i in range(1,len(files)):
        data = pd.read_csv(dir_name + '/' + files[i])
        data['date'] = pd.to_datetime(data['Date'], format='%Y%m%d', errors='ignore')
        mask = (data['date'] > start_date) & (data['date'] < end_date)
        d = data.loc[mask]
        f = d['Close']
        k = f.reset_index(drop=True)
        result = result + k
        if ((target_name + ".txt") == files[i]):
            target = k
    return target,result
#get sum of all stock close values

if __name__ == '__main__':

    a = []
    for i in range(1,len(sys.argv)):
        a.append(((sys.argv[i])))

    target,result = get_format(a[0],a[1],a[2],a[3])
    calculate_estimate(target,result)


# data2 = pd.read_csv(abs_path + "/KO.txt")
# print(data2)
#
# data3 = pd.read_csv(abs_path + "/WMT.txt")
# print(data3)


