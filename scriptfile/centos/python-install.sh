#!/usr/bin/bash
wget https://www.python.org/ftp/python/3.7.0/Python-3.7.0.tgz
sudo yum install libffi-devel -y
tar -xzf Python-3.7.0.tgz
cd Python-3.7.0
./configure
make
sudo make install

sudo /usr/local/bin/pip3 install websocket-client