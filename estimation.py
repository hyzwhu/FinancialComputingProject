import pandas as pd
import os
import numpy as np
import glob
import codecs
import statsmodels.api as sm
from statsmodels import regression
import sys

def calculate_estimate(df1,df2):

    return_target = df1.pct_change()[1:]
    return_all = df2.pct_change()[1:]



    def linreg(x,y):
        x = sm.add_constant(x)
        model = regression.linear_model.OLS(y,x).fit()
        x = x[:,1]
        return model.params[0], model.params[1]


    alpha, beta = linreg(return_all.values, return_target.values)
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



