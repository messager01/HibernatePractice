# HibernatePractice
OID  查询   
      什么是OID查询？  根据对象的OID 进行检索
OID  的查询方式
   get方法：
           当get方法被调用时，会立即发出SQL语句，并且返回的对象也是实际对象，当查询不到时，get查询返回的是null
   load方法：
           调用load方法时，会返回一个目标对象的代理对象，在这个代理对象中值存储了目标对象的ID值
           当只有调用ID以外的属性时，才发SQL语句进行查询
           当查询不到时，load()会报错
