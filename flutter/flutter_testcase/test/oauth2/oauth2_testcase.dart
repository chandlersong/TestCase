import 'dart:io';

import 'package:oauth2/oauth2.dart';
import 'package:oauth2/oauth2.dart' as oauth2;
import 'package:test_api/test_api.dart';

void main() {
  String authorizationServer = "http://localhost:18081";
  String resourceServer = "http://localhost:8081";

  test('test simple login', () async {
    /**
     * it will refresh token automatically
     */
    String userName = "user";
    String password = "password";
    String client = "client";
    String secret = "secret";

    Client template = await oauth2.resourceOwnerPasswordGrant(
        Uri.parse("$authorizationServer/oauth/token"), userName, password,
        identifier: client, secret: secret);

    sleep(const Duration(seconds: 10));
    var response = await template.get(Uri.parse("$resourceServer/resource"));
    print(response.body);
  });
}
