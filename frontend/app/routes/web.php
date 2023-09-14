<?php

use App\Http\Controllers\Shared\SearchController;
use App\Http\Controllers\Shared\ComposerController;
use App\Http\Controllers\Shared\MusicalWorkController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Shared\ResourceController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
  return view('web.home.home');
});

Route::get('/about', function () {
  return view('web.home.about');
});

Route::get('/search', [SearchController::class, 'search']);

Route::get('/resource/external-datasets', [ResourceController::class, 'getExternalDatasets']);
Route::post('/resource/link-external-entity', [ResourceController::class, 'linkExternalEntity']);
Route::delete('/resource/unlink-external-entity', [ResourceController::class, 'unlinkExternalEntity']);
Route::get('/resource/{id}', [ResourceController::class, 'show']);

Route::get('/composer/create', [ComposerController::class, 'create']);
Route::post('composer/create', [ComposerController::class, 'store']);
Route::get('/composer/{id}', [ComposerController::class, 'show']);

Route::get('/musicalWork/create', [MusicalWorkController::class, 'create']);
Route::post('/musicalWork/create', [MusicalWorkController::class, 'store']);
Route::get('/musicalWork/{id}', [MusicalWorkController::class, 'show']);
