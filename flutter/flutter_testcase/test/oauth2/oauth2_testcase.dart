import 'dart:io';

import 'package:http/http.dart';
import 'package:oauth2/oauth2.dart' as oauth2;
import 'package:test_api/test_api.dart';

void main() {
  String authorizationServer = "http://localhost:8080";
  String resourceServer = "http://localhost:8081";
  String userName = "user";
  String password = "password";
  String client = "client";
  String secret = "secret";

  test('test simple login', () async {
    oauth2.Client template = await oauth2.resourceOwnerPasswordGrant(
        Uri.parse("$authorizationServer/oauth/token"), userName, password,
        identifier: client, secret: secret);

    sleep(const Duration(seconds: 10));
    Response response = await template.get(Uri.parse("$resourceServer/resource"));
    print(response.body);
  });

  test('create with credential', () async {
    oauth2.Client template = await oauth2.resourceOwnerPasswordGrant(
        Uri.parse("$authorizationServer/oauth/token"), userName, password,
        identifier: client, secret: secret);

    sleep(const Duration(seconds: 10));

    var credentials = new oauth2.Credentials.fromJson(template.credentials.toJson());
    oauth2.Client templateNew = new oauth2.Client(credentials, identifier: client, secret: secret);


    Response response = await templateNew.get(Uri.parse("$resourceServer/resource"));
    print(response.body);
  });
}
