const mix = require('laravel-mix');

/*
 |--------------------------------------------------------------------------
 | Mix Asset Management
 |--------------------------------------------------------------------------
 |
 | Mix provides a clean, fluent API for defining some Webpack build steps
 | for your Laravel applications. By default, we are compiling the CSS
 | file for the application as well as bundling up all the JS files.
 |
 */

mix.js('resources/js/app.js', 'public/js')
    .js('resources/js/makeRequest.js', 'public/js')
    .js('resources/js/musicalWork.js', 'public/js')
    .js('resources/js/composer.js', 'public/js')
    .js('resources/js/resource.js', 'public/js')
    .js('resources/js/createComposer.js', 'public/js')
    .js('resources/js/createMusicalWork.js', 'public/js')
    .js('resources/js/externalData.js', 'public/js')
    .postCss('resources/css/app.css', 'public/css', [
        require("tailwindcss"),
    ]).copy( 'resources/svg', 'public/svg');
