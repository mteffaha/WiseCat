// Generated by CoffeeScript 1.8.0
(function() {
  FB.statusChangeCallback = function(response) {
    console.log(response);
    if (response.status === 'connected') {
      return console.log(response.authResponse.userID);
    } else {
      return FB.login(FB.statusChangeCallback, {
        scope: 'public_profile,email,user_friends'
      });
    }
  };

  $(function() {
    return FB.getLoginStatus(FB.statusChangeCallback);
  });

}).call(this);
