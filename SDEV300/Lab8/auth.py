"""auth routes"""
import logging
import re
from flask import Blueprint, render_template, redirect, url_for, request, flash, current_app
from werkzeug.security import generate_password_hash, check_password_hash
from flask_login import login_user, logout_user, login_required
from models import User
from init import db


#  log level and format
logging.basicConfig(filename='record.log', level=logging.INFO,
                    format=f'%(asctime)s %(levelname)s %(name)s: %(message)s')
#  disable other logs except login attempts
logging.getLogger('werkzeug').disabled = True
auth = Blueprint('auth', __name__)  # create a Blueprint named 'auth'


@auth.route('/login', methods=['GET', 'POST'])  # login page path
def login():  # login page function
    """login"""
    if request.method == 'GET':  # if the request is a GET return the login page
        return render_template('login.html')
    # if the request is POST check if the user exist and with the right password
    email = request.form.get('email')
    password = request.form.get('password')
    remember = True if request.form.get('remember') else False
    user = User.query.filter_by(email=email).first()
    # check if the user actually exists
    # take the user-supplied password, hash it, and compare to in the database
    if not user:
        flash('Please sign up before!')
        current_app.logger.info("ip: {}, Failed login attempt".format(request.remote_addr))
        return redirect(url_for('auth.login'))
    if not check_password_hash(user.password, password):
        flash('Please check your login info and try again.')
        current_app.logger.info("ip: {}, Failed login attempt".format(request.remote_addr))
        # if the user doesn't exist or password is wrong, reload the page
        return redirect(url_for('auth.login'))
    # if the above check passes, then user is verified
    login_user(user, remember=remember)
    return redirect(url_for('main.home'))  # once logged in redirect to index page


@auth.route('/profile', methods=['GET', 'POST'])
def profile():
    """profile"""
    return render_template('profile.html')


@auth.route('/signup', methods=['GET', 'POST'])  # we define the sign-up path
def signup():  # define the sign-up function
    """sign up"""
    if request.method == 'GET':  # If the request is GET we return the sign-up page and forms
        return render_template('signup.html')
    # if the request is POST, then check if the email doesn't already exist, and then we save data
    email = request.form.get('email')
    name = request.form.get('name')
    password = request.form.get('password')
    # complex password format
    reg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!#%*?&]{6,20}$"
    pat = re.compile(reg)
    complexpass = re.search(pat, password)
    # compare password with list of common passwords from txt file
    with open("CommonPassword.txt", 'r', encoding='utf-8') as f:
        common_values = [line.rstrip("\n") for line in f]
    if any(value == password for value in common_values):
        flash("Don't use a common password, try another one.")
        return redirect(url_for('auth.signup'))  # if password is not valid return to sign up
    # check if password is complex
    if not complexpass:
        flash('Password must be 6-20 characters, with at least 1 number, 1 uppercase letter, and 1 special character.')
        return redirect(url_for('auth.signup'))  # if password is not valid return to sign up
    user = User.query.filter_by(
        email=email).first()  # if this returns a user, then the email already exists in database
    if user:  # if a user is found,redirect back to signup page so user can try again
        flash('Email address already exists')
        return redirect(url_for('auth.signup'))  # if email exists return to sign up
    # create a new user with the form data. Hash the password.
    new_user = User(email=email, name=name,
                    password=generate_password_hash(password, method="sha256"))
    # add the new user to the database
    db.session.add(new_user)
    db.session.commit()
    return redirect(url_for('auth.login'))  # once successfully signed up redirect to login page


@auth.route('/change-pass', methods=['GET', 'POST'])
@login_required
def change_pass():
    """change password"""
    if request.method == 'GET':  # If the request is GET we return the sign-up page
        return render_template('change-pass.html')
    email = request.form.get('email')
    new_pass = request.form.get('password-change')
    reg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!#%*?&]{6,20}$"
    pat = re.compile(reg)
    complexpass = re.search(pat, new_pass)
    # compare password with list of common passwords from txt file
    with open("CommonPassword.txt", 'r', encoding='utf-8') as f:
        common_values = [line.rstrip("\n") for line in f]
    if any(value == new_pass for value in common_values):
        flash("Don't use a common password, try another one.")
    # check if password is complex
    if not complexpass:
        flash('Password must be 6-20 characters, with at least 1 number, 1 uppercase letter, and 1 special character.')
        return redirect(url_for('auth.change_pass'))
    user = User.query.filter_by(email=email).first()
    # if user email is verified then change password
    if user:
        user.password = generate_password_hash(new_pass, method='sha256')
        db.session.commit()
        flash('Password changed')
        return redirect(url_for('auth.change_pass'))
    flash('wrong password')
    return redirect(url_for('auth.change_pass'))


@auth.route('/logout')  # define logout path
@login_required
def logout():  # define the logout function
    """logout function"""
    logout_user()
    return redirect(url_for('main.main_page'))  # once logged out return to index page
