import pandas as pd

df = pd.read_excel('loan-data-v1.xlsx')
df1 = df[['Name', 'Annual Income', 'Loan Type', 'Loan Amount']]
df1.to_html('result2.html')