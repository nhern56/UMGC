"""init"""
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager


# init SQLAlchemy
db = SQLAlchemy()


def create_app():
    """create app function"""
    app = Flask(__name__)  # creates the Flask instance
    app.config['SECRET_KEY'] = 'secret-key-goes-here'  # keep data safe
    # path where the SQLite database file will be saved
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///db.sqlite'
    # deactivate Flask-SQLAlchemy track modifications
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    db.init_app(app)  # Initialize sqlite database
    # login manager lets your application and Flask-Login work together
    login_manager = LoginManager()  # Create a Login Manager instance
    login_manager.login_view = 'auth.login'  # define the redirection path when login required
    login_manager.init_app(app)  # configure for login
    from models import User

    @login_manager.user_loader
    def load_user(user_id):  # reload user object from the user ID stored in the session
        # use for query for the user
        return User.query.get(int(user_id))
    # blueprint for auth routes in our app
    # blueprint allow you to organize your flask app
    from auth import auth as auth_blueprint
    app.register_blueprint(auth_blueprint)
    # blueprint for non-auth parts of app
    from main import main as main_blueprint
    app.register_blueprint(main_blueprint)
    return app
