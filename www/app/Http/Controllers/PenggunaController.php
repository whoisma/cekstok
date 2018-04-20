<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Model\Pengguna;
use Validator;
use Carbon\Carbon;


class PenggunaController extends Controller {

	public function store(Request $request){
        $rules = [
            'phone' => 'required'
        ];

        $input = $request->only('phone');

        $validator = Validator::make($input, $rules);

        if($validator->fails()) {
            $error = $validator->messages()->toJson();


            return $this->fmt(false, "input tidak valid !", $error);
        }

        $data = Pengguna::where('NOHP', $request->input('phone'))->first();

        if (count($data)){

            $tgl = Carbon::now('Asia/Jakarta');

            $tmp = [
                'user'  => $data['nama'],
                'phone' => $data['nohp'],
                'tgl'   => $tgl
            ];

            $token = json_encode($tmp);

            $user = [
                'user'  => $data,
                'token' => base64_encode($token)
            ];

            return $this->fmt(true, 'Data Pengguna', $user);
        }else{
            $tmp = [
                'user'  => '',
                'phone' => '',
                'tgl'   => ''
            ];

            $user = [
                'user'  => $data,
                'token' => ''
            ];

            return $this->fmt(false, 'Data pengguna tidak ada !', $user);
        }
	}

	public function me(Request $request){

	    $str = $request->query('keys');

	    return $str;
    }
}
