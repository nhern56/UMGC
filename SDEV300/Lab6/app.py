"""lab 6 Python Web Page Code"""
from datetime import datetime
from flask import Flask
from flask import render_template
app = Flask(__name__)


# render home page + date and time
@app.route('/')
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
