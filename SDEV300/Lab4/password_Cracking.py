import hashlib

while True:
    print('Enter a message to encode:')
    message = input()
    message = message.encode()
    # md5 hash
    print(hashlib.md5(message).hexdigest())
    # sha256 hash
    print(hashlib.sha256(message).hexdigest())
    # sha512 hash
    print(hashlib.sha512(message).hexdigest())
    print("")
