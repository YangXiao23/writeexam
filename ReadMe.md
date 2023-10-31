完成功能：

- ldap、数据库、github登录

- product的crud以及权限控制

- docker comopse部署，docker-compose.env.yml为mysql等依赖服务，docker-compose.service.yml为java微服务模块。



```shell
//ldap登录
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d "username=YOUR_USERNAME&password=YOUR_PASSWORD" http://localhost:7573/oss/ldap/login
//数据库登录
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d "username=YOUR_USERNAME&password=YOUR_PASSWORD" http://localhost:7573/oss/sql/login
//浏览产品
curl -X GET -H "Content-Type: application/x-www-form-urlencoded" -H "Authorization: yourtoken"  http://localhost:7573/product/list
//编辑产品
curl -X POST-H "Content-Type: application/x-www-form-urlencoded" -H "Authorization: yourtoken"  -d "id=product_id&name=product_name" http://localhost:7573/product/update
//删除产品
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -H "Authorization: yourtoken"  -d "id=product_id" http://localhost:7573/product/delete
//添加产品
curl -X GET -H "Content-Type: application/x-www-form-urlencoded" -H "Authorization: yourtoken"  -d "name=product_name" http://localhost:7573/product/add
```

```shell
//github登录需要在浏览器上进行，同时需要在github设置好回调地址,我配置的oauthapp设置回调为http://localhost:7573/login/oauth2/code/github
验证地址：http://localhost:7573/oauth2/authorization/github
```


