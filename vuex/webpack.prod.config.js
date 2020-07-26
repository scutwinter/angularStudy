const webpack = require('webpack');
const HtmlwebpackPlugin =require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const {merge} = require('webpack-merge');
const webpackBaseConfig=require('./webpack.config.js');

//清空基本配置的插件列表
webpackBaseConfig.plugins = [];

module.exports = merge(webpackBaseConfig,{
    output:{
        publicPath:'./dist/',
        //将入口文件重命名为带有20位hash置的唯一文件
        filename:'[name].[hash].js',
        chunkFilename:'[name].[hash].chunk.js'
    },
    mode:'production',
    plugins:[
        new MiniCssExtractPlugin({
            filename:'[name].[hash].css',
            chunkFilename: '[id].[hash].chunk.css'
        }),
        // //定义当前node环境为生产环境
        // new webpack.DefinePlugin({
        //     'process.env':{
        //         NODE_ENV: '"production"'
        //     }
        // }),
        //压缩js
        // new webpack.optimize.UglifyJsPlugin({
        //     compress:{
        //         warnings:false
        //     }
        // }),
        //提取末班，并保存入口html文件
        new HtmlwebpackPlugin({
            filename:'../index_prod.html',
            template:'./index.ejs',
            inject:false
        }),
        new VueLoaderPlugin()
    ]
});
