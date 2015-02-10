#!/bin/sh
# This script build & run WiseCat node server
# How cool :]
# For Sublime Text 3 : point a symbolic link to this file from /usr/bin/ for Sublime's build system to find it

#####################################################################
# 							Variables								#
#####################################################################

# Dir root
SCRIPT=$(readlink -f "$0")
ROOT=$(dirname "$SCRIPT")
# App
APP=$ROOT/app
MODULES=$ROOT/app/modules
STATIC=$ROOT/app/static
# Src
SRC=$ROOT/src
JADE=$SRC
STYLUS=$SRC/stylus
COFFEE=$SRC/coffee
# Server
SERVER=$APP/server.js
# Local client URL
CLIENT=localhost:3000/
# Date
DATE=`date +%F-%H-%M-%S`

#####################################################################
# 			Cleaning : move files to .thrash/YYYY-MM-DD/			#
#####################################################################

# Thrash
mkdir $ROOT/.thrash/
mkdir $ROOT/.thrash/$DATE/
mkdir $ROOT/.thrash/$DATE/modules
# mkdir $ROOT/.thrash/$DATE/static

# Server
mv $APP/server.js $ROOT/.thrash/$DATE/

# Statics
# mv $STATIC/*.html $ROOT/.thrash/$DATE/static
# mv $STATIC/*.js $ROOT/.thrash/$DATE/static
# mv $STATIC/*.css $ROOT/.thrash/$DATE/static

# Modules
mv $MODULES/*.js $ROOT/.thrash/$DATE/modules

#####################################################################
# 							Compile									#
#####################################################################

# Server
coffee -o $APP -c $COFFEE/server.coffee

# Statics
# jade -P $JADE -o $STATIC
# stylus $STYLUS -o $STATIC
# coffee -o $STATIC -c $COFFEE/static

# Modules
coffee -o $MODULES -c $COFFEE/modules

#####################################################################
# 							Run										#
#####################################################################

# Firefox
firefox -new-tab $CLIENT

# Chrome
# google-chrome --app=$CLIENT

# Idiot !
echo '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'
echo 'You HAVE to run the server you idiot !'
echo "> node $SERVER"
echo '~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'