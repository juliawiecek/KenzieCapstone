const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    commentElements: path.resolve(__dirname, 'src', 'elements', 'commentElements.js'),
    commentPage: path.resolve(__dirname, 'src', 'pages', 'commentPage.js'),
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
    proxy: [
      {
        context: [
          '/api/comments',
        ],
        target: 'http://localhost:5001'
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/html/comment.html',
      filename: 'comment.html',
      inject: false
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css")
        }
//        {
//          from: path.resolve('src/api'),
//          to: path.resolve("dist/api")
//        },
//        {
//          from: path.resolve('src/elements'),
//          to: path.resolve("dist/elements")
//        },
//        {
//          from: path.resolve('src/pages'),
//          to: path.resolve("dist/pages")
//        },
//        {
//          from: path.resolve('src/html'),
//          to: path.resolve("dist/html")
//        },
//        {
//          from: path.resolve('src/util'),
//          to: path.resolve("dist/util")
//        }
      ]
    }),
    new CleanWebpackPlugin()
  ]
}
