import express from 'express';
import path from 'path';
import open from 'open';
import compression from 'compression';
import proxy from 'http-proxy-middleware';

/*eslint-disable no-console */

const port = 3000;
const app = express();

var options = {
        target: 'http://localhost:8081/tdm', // target host
        changeOrigin: true,               // needed for virtual hosted sites       
        router: {            
            'http://localhost:3000' : 'http://localhost:8080'
        }
    };
var reverseproxy = proxy(options);

app.use(compression());
app.use(express.static('dist'));
app.use('/api', reverseproxy);
app.get('*', function(req, res) {
  res.sendFile(path.join(__dirname, '../dist/index.html'));
});

app.listen(port, function(err) {
  if (err) {
    console.log(err);
  } else {
    //open(`http://localhost:${port}`);
  }
});
