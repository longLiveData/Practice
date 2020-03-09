'''
程序的主入口
'''
from flask import Flask
from blue import blue

app = Flask(__name__)
app.register_blueprint(blue, url_prefix = '/')

if __name__=='__main__':
    # app.run()
    app.run(host='127.0.0.1',port=80,debug=True)