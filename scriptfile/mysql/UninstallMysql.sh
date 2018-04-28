#!/usr/bin/bash
rpm -ev --nodeps `rpm -qa | grep -i mysql`
rm -fr `find / -iname mysql`