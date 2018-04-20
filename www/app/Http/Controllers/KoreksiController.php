<?php

namespace App\Http\Controllers;


use Illuminate\Http\Request;
use Validator;
use App\Model\Koreksi;
use Carbon\Carbon;

class KoreksiController extends Controller {


	public function index(){
		$data = Koreksi::all();

        return response()
            ->json($data);
    }

    public function store(Request $request){
        $rules = [
            'toko'          => 'required',
            'upc'           => 'required',
            'jumlah_stock'  => 'required',
            'lokasi'        => 'required',
        ];

        $input = $request->only('toko', 'upc', 'jumlah_stock', 'lokasi');

        $validator = Validator::make($input, $rules);

        if($validator->fails()) {
            $error = $validator->messages()->toJson();


            return $this->fmt(false, "input tidak valid !", $error);
        }

        $tgl = Carbon::now('Asia/Jakarta')->format('Y-m-d H:i:s');

        $data = new Koreksi();

        $data->TGL_INPUT    = new \DateTime();
        $data->TOKO         = $request->toko;
        $data->UPC          = $request->upc;
        $data->JUMLAH_STOCK = $request->jumlah_stock;
        $data->LOKASI_AREA  = $request->lokasi;
        $data->save();

        return $this->fmt(true, 'Simpan Koreksi Stock berhasil !');
    }
}
