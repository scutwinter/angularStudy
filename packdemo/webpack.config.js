const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const config = {
    entry:{
        main: './main'
    },
    output:{
        path: path.join(__dirname,'./dist'),
        publicPath:'/dist/',
        filename:'main.js'
    },
    module:{
        rules:[
            {
                test:/\.css$/,
                // use:[
                //     'style-loader',
                //     'css-loader'
                // ]
                // use: ExtractTextPlugin.extract({
                //     use:'css-loader',
                //     fallback:'style-loader'
                // })
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader
                    },
                    'css-loader'
                ]
            },
            {
                test: /\.(png|jpg|gif)$/,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 1024,
                            esModule:false
                        }
                    }
                ]
            },
            {
                test:/\.vue$/,
                loader: 'vue-loader'
            },
            {
                test:/\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            }

        ]
    },
    plugins:[
        new MiniCssExtractPlugin({
            filename:'main.css'
        }),
        // make sure to include the plugin for the magic
        new VueLoaderPlugin()
    ]
};
module.exports = config;
