users = [
    {"name": "kostykevich_a", "password": "12311231"}
]


def get_user(name):
    try:
        return (user for user in users if user["name"] == name).next()
    except:
        print("\n     User %s is not defined, enter a valid user.\n" % name)
