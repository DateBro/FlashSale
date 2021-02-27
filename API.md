### API

#### 用户注册

```
POST /buyer/register
```

参数

```
telphone: "18868822111"
otpCode: "996485"
username: "管理员"
gender: 1
age: 18
password: 123456
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
          "buyerId": "161899085773669363",
          "username": "李四",
          "gender": "18868877111",
          "age": "18",
          "telephone": "18868877111",
    }
}
```

### 获取手机验证码

```
POST /buyer/getotp
```

参数

```
telphone: "18868822111"
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": null
}
```

### 获取买家信息

```
POST /buyer/buyerInfo
```

参数

```
buyerId: "18868822111"
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
          "buyerId": "161899085773669363",
          "username": "李四",
          "gender": "18868877111",
          "age": "18",
          "telephone": "18868877111",
    }
}
```

### 用户登录

```
POST /buyer/login
```

参数

```
telephone: "18868822111"
password: "123456"
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": null
}
```

### 创建商品

```
POST /product/create
```

参数

```
productName: "iPhone8"
productDescription: "非常好用的一款手机"
productPrice: 2000.00
productStock: 100
productIcon: "https://img.pconline.com.cn/images/product/6272/627291/iPhone8-X2_sn8.jpg"
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
    productName: "iPhone8"
    productDescription: "非常好用的一款手机"
    productPrice: 2000.00
    productStock: 100
    productIcon: "https://img.pconline.com.cn/images/product/6272/627291/iPhone8-X2_sn8.jpg"
  }
}
```

### 回补库存

```
GET /product/increaseStock
```

参数

```
productId: 1
productQuantity2Increase: 6
```

返回
```
{
  "code": 0,
  "msg": "成功",
  "data": null
}
```

### 上线秒杀活动（将库存同步至redis）

```
GET /promo/publishPromo
```

参数

```
promoId: 1
```

返回
```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "promoId":7,
      "promoName":"秒杀测试",
      "productId":1,
      "promoStatus":2,
      "promoProductPrice":100.00,
      "promoStartTime":"2021-02-18 15:30:00.0",
      "promoEndTime":"2021-02-19 15:30:00.0",
  }
}
```

### 获取商品列表

```
GET /product/list
```

参数

```
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "productId": 1,
            "productName": "iPhone8",
            "productPrice": 2000.00,
            "productSales": 0,
            "stock": 100,
            "productDescription": "非常好用的一款手机",
            "productIcon": "https://img.pconline.com.cn/images/product/6272/627291/iPhone8-X2_sn8.jpg",
            "productStatus": 0,
            "promoStatus":2,
            "promoProductPrice":100.00,
            "promoId":7,
            "promoStartTime":"2021-02-18 15:30:00.0"
        },
        {
            "productId": 2,
            "productName": "华为Mate30",
            "productPrice": 1600.00,
            "productSales": 0,
            "stock": 100,
            "productDescription": "一款好用的安卓手机",
            "productIcon": "https://2a.zol-img.com.cn/product/201_120x90/592/cewznHYD4wOFs.jpg",
            "productStatus": 0,
            "promoStatus":2,
            "promoProductPrice":100.00,
            "promoId":7,
            "promoStartTime":"2021-02-18 15:30:00.0"
        },
        {
            "productId": 3,
            "productName": "一加7",
            "productPrice": 1200.00,
            "productSales": 0,
            "stock": 50,
            "productDescription": "好用的安卓手机",
            "productIcon": "https://2b.zol-img.com.cn/product/198_100x75/223/ceBKXPTsBtKIQ.jpg",
            "productStatus": 0
        }
    ]
}
```

### 获取商品详情

```
GET /product/productDetail
```

参数

```
productId: 1
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "productId": 1,
            "productName": "iPhone8",
            "productPrice": 2000.00,
            "productSales": 0,
            "stock": 100,
            "productDescription": "非常好用的一款手机",
            "productIcon": "https://img.pconline.com.cn/images/product/6272/627291/iPhone8-X2_sn8.jpg",
            "productStatus": 0,
            "promoStatus":2,
            "promoProductPrice":100.00,
            "promoId":7,
            "promoStartTime":"2021-02-18 15:30:00.0"
        },
    ]
}
```

### 创建订单

```
POST /order/create
```

参数

```
productId: 1
productQuantity: 1
promoId: 6
promoToken: xxxx
```

返回
```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

### 生成图形验证码

```
GET /order/genVerifyCode
```

参数

```
```

返回
```
```

### 生成秒杀token

```
POST /order/genToken
```

参数

```
productId: 1
promoId: 6
verifyCode: at6x
```

返回
```
{
  "code": 0,
  "msg": "成功",
  "data": {
    promoToken: "xxxxxxxxxx"
  }
}
```