#!/usr/bin/bash
wget https://www.python.org/ftp/python/3.7.0/Python-3.7.0.tgz
sudo yum install libffi-devel -y
tar -xzf Python-3.7.0.tgz
cd Python-3.7.0
./configure
make && make install

python3 -m ensurepip --default-pip
python3 -m pip install --upgrade pip setuptools wheel
sudo /usr/local/bin/pip3 install websocket-client