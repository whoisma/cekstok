<?php

use Illuminate\Http\Request;


Route::group(['middleware' => ['cors']], function() {
    Route::get('/login', 'PenggunaController@me');

    Route::get('/products', 'StockController@all');
    Route::get('/product/{toko}/{id}', 'StockController@product');
    Route::post('/koreksi', 'KoreksiController@store');
});
