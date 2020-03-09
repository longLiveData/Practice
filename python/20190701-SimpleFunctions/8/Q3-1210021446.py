import pandas as pd

df = pd.read_excel('loan-data-v1.xlsx')
df1 = df[['Age', 'Days Delinquent', 'Loan Amount']]
df1.to_json('result3.json')