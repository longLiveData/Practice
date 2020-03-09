from flask import Blueprint
from flask import Flask
from flask import render_template
from flask import url_for
from flask import request
from flask import json
from flask import jsonify
from flask import Response
import configparser
import pymysql

import time

blue = Blueprint('post',__name__)

@blue.route('/')
def first():
    return render_template("first.html")

@blue.route('/perTest',methods=['get'])
def preTest():
    return render_template("preTest.html")


@blue.route('/test',methods=['get'])
def test():
    return render_template("test.html")

@blue.route('/calc',methods=['post'])
def calc():
    # 数据处理
    curTime = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    curIp = request.remote_addr
    sex = request.form.get('sex')
    poli = request.form.get('poli')
    grade = request.form.get('grade')
    ctype = request.form.get('type')
    cTerm = request.form.get('term')
    cCorr = request.form.get('corr')
    cTime = request.form.get('time')

    resStr = ""
    resStr += curTime + " "
    resStr += curIp + " "
    resStr += sex + " "
    resStr += poli + " "
    resStr += grade + " "
    resStr += ctype + " "
    resStr += cTerm
    resStr += cCorr
    resStr += cTime

    # 写入文件
    f = open('record.txt','a',encoding="utf-8")
    f.write(resStr+'\n')
    f.close()

    res = {}
    return json.dumps(res,ensure_ascii=False)


    
