import express from 'express';
import webpack from 'webpack';
import path from 'path';
import config from '../webpack.config.dev';
import open from 'open';
import proxy from 'http-proxy-middleware';

/* eslint-disable no-console */

const port = 3000;
const app = express();
const compiler = webpack(config);

var options = {
        target: 'http://192.168.99.100:8081/tdm', // target host
        changeOrigin: true,               // needed for virtual hosted sites       
        router: {            
            'http://localhost:3000' : 'http://localhost:8080'
        }
    };

var exampleProxy = proxy(options);

app.use(require('webpack-dev-middleware')(compiler, {
  noInfo: true,
  publicPath: config.output.publicPath
}));

app.use(require('webpack-hot-middleware')(compiler));
app.use('/api', exampleProxy);
app.get('*', function(req, res) {
  res.sendFile(path.join( __dirname, '../src/index.html'));
});

app.listen(port, function(err) {
  if (err) {
    console.log(err);
  } else {
    open(`http://localhost:${port}`);
  }
});