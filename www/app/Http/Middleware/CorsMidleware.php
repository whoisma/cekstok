<?php
/**
 * Created by PhpStorm.
 * User: MA
 * Date: 07/01/2018
 * Time: 23:03
 */

namespace App\Http\Middleware;

use Closure;


class CorsMidleware{

    public function handle($request, Closure $next){

        $response = $next($request);

        $response->header('Access-Control-Allow-Methods', 'HEAD, GET, POST, PUT, PATCH, DELETE');
        $response->header('Access-Control-Allow-Headers', $request->header('Access-Control-Request-Headers'));
        $response->header('Access-Control-Allow-Origin', '*');

        return $response;
    }

}