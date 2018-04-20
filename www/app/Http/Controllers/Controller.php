<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Model\Pengguna;

class Controller extends BaseController{

    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    protected function fmt($status, $msg,  $data = [], $code = 200){

        return response()->json([
            'status'    => $status,
            'msg'       => $msg,
            'data'      => $data,
        ], $code);
    }

    protected function isAuth(Request $request){
        $token = $request->header("Authorization");

        if (!$token){

            return ['status' => false, 'data' => []];
        }

        $json = base64_decode($token);

        return ['status' => true, 'data' => $json];
    }
}
