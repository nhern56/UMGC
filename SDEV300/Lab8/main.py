"""Main routes"""
from flask import Blueprint, render_template
from init import create_app, db


# our main blueprint
main = Blueprint('main', __name__)


# home page
@main.route('/home')
def home():
    """home page"""
    return render_template('browse_movies.html')


@main.route('/mylist')
def mylist():
    """my list"""
    return render_template('mylist.html')


@main.route('/main')
def main_page():
    """main page"""
    return render_template('netflixclone.html')


app = create_app()  # we initialize flask app

if __name__ == '__main__':
    db.create_all(app=create_app())  # create the SQLite database
    app.run(debug=True)  # run the flask app on debug mode
