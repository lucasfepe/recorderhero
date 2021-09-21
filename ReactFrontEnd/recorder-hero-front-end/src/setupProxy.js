const  createProxyMiddleware  = require('http-proxy-middleware');


module.exports = function(app) {
  app.use(
    createProxyMiddleware(['/game', '/progress','/report','/sessions', '/library'],{
      target: 'http://localhost:8082',
      changeOrigin: true
      
    })
  );
};
 