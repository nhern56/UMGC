"""lab 7"""
from datetime import datetime
import re
from passlib.hash import sha256_crypt
from flask import Flask, render_template, request

app = Flask(__name__)


@app.route('/')
def register():
    """register page"""
    if request.method == 'POST' 'GET':
        username = request.form['username']
        email = request.form['email']
        password = request.form['password']
        hash_pass = sha256_crypt.hash(password)
        if len(password) < 8:
            print("Make sure your password is at lest 8 letters")
        elif re.search(r'\d', password) is None:
            print("Make sure your password has a number in it")
        elif re.search('[A-Z]', password) is None:
            print("Make sure your password has a capital letter in it")
        else:
            print("Your password seems fine")
        # Open file
        with open('passfile', "a", encoding='utf-8') as d_b:
            d_b.write(str(username) + str(email) + str(hash_pass))
    return render_template('register.html')


@app.route('/login/')
def login():
    """login page"""
    error = None
    if request.method == 'POST' 'GET':
        username = request.form['username']
        email = request.form['email']
        password = request.form['password']
        if request.form['username'] != username:
            error = 'Please enter your Username.'
        elif request.form['email'] == email:
            error = 'You are already registered'
        elif not password:
            error = 'Please enter your Password.'
        elif request.form['password'] != password:
            error = 'wrong password'
    return render_template('login.html', error=error)


# render home page + date and time
@app.route('/home/')
def index():
    """home page"""
    now = datetime.now()
    date_time = now.strftime("%d/%m/%Y, %H:%M:%S")
    return render_template('Home.html', date_time=date_time)


# netflix link + date and time
@app.route('/netflix/')
def netflix():
    """netflix page"""
    now = datetime.now()
    date_time = now.strftime("%d/%m/%Y, %H:%M:%S")
    return render_template('netflix.html', date_time=date_time)


# hulu link + date and time
@app.route('/hulu/')
def hulu():
    """hulu web page"""
    now = datetime.now()
    date_time = now.strftime("%d/%m/%Y, %H:%M:%S")
    return render_template('hulu.html', date_time=date_time)


# prime link + date and time
@app.route('/prime/')
def prime():
    """prime web page"""
    now = datetime.now()
    date_time = now.strftime("%d/%m/%Y, %H:%M:%S")
    return render_template('prime.html', date_time=date_time)


# run code
if __name__ == '__main__':
    app.run(debug=True)
