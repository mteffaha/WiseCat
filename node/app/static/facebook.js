// Generated by CoffeeScript 1.8.0
(function() {
  FB.connect = function() {
    return FB.getLoginStatus(FB.statusChangeCallback);
  };

  FB.statusChangeCallback = function(response) {
    if (response.status === 'connected') {
      FB.user.id = response.authResponse.userID;
      FB.user.token = response.authResponse.accessToken;
      return FB.api('/me', function(response) {
        return FB.user.name = response.first_name;
      });
    } else {
      return FB.login(FB.statusChangeCallback);
    }
  };

  FB.user = {
    id: '',
    name: '',
    token: ''
  };

  $(function() {
    return FB.connect();
  });

}).call(this);
