import os
import pandas as pd
from sys import argv

#批量读入股票数据
#选取Close value


macd_in = argv[1]
##rise_in = argv[2]
dir_name = argv[2]

# print("macd_in:" + macd_in)
# print("dir_name:" + dir_name)

files = os.listdir(dir_name)
datas = set()
macd_dict = {}
rise_dict = {}
    
for file in files:
    path = os.path.join(dir_name, file)
    dat = pd.read_csv(path, index_col=False)
    price_op = dat['Open']
    price_cl = dat['Close']

    ewm_26 = price_cl.ewm(span=26).mean()
    ewm_12 = price_cl.ewm(span=12).mean()

    #calculate macd of each stock
    macd = ewm_12 - ewm_26
    
    #based on result
    #select the last open/close price and calculate the percentage
    rise = (price_cl - price_op)/price_op
    
    macd_dict[file[:2]] = macd.iloc[-1]
    rise_dict[file[:2]] = rise.iloc[-1]
    

#based on result
#select the last open/close price and calculate the percentage

#rank the stocks based on the percentage
sorted_macd = sorted(macd_dict.items(), key=lambda x:x[1], reverse=True)
sorted_rise = sorted(rise_dict.items(), key=lambda x:x[1], reverse=True)


def getlist(array):
    li = []
    for i in range(0, len(array)):
        li.append(array[i][0])
    return li


if int(macd_in) == 1:
    print(getlist(sorted_macd))
else:
    print(getlist(sorted_rise))

