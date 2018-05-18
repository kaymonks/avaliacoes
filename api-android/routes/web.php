<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});
$router->get('/key', function () use ($router) {
    return str_random(32);
});
$router->group(['prefix' => 'api'], function () use ($router) {
    $router->get('reclamations', ['uses' => 'ReclamationController@showAll']);
    $router->get('reclamation/{id}', ['uses' => 'ReclamationController@show']);
    $router->post('reclamation', ['uses' => 'ReclamationController@create']);
    $router->put('reclamation/{id}', ['uses' => 'ReclamationController@update']);
    $router->delete('reclamation/{id}', ['uses' => 'ReclamationController@delete']);
});

