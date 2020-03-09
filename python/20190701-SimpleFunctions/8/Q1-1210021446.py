import pandas as pd

df = pd.read_csv('loan-data-v1.csv')

# html
df.to_html('loan-data-v1.html')

# xlsx
df.to_excel('loan-data-v1.xlsx')

# json
df.to_json('loan-data-v1.json')