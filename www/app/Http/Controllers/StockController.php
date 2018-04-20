<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Model\Stock;

class StockController extends Controller {


	public function index(){
		$data = Stock::all();

        return response()
            ->json($data);
    }

    public function all(Request $request){
	    $offset = 0;
	    $limit = 10;

	    if ($request->input('offset')){
	        $offset = $request->input('offset');
        }

        $query = strtoupper($request->input('qw'));

        $data = Stock::where('UPC', 'like', '%'. $query .'%')
            ->orWhere('NAMA_BARANG', 'like', '%'. $query .'%')
            ->orWhereHas('supplier', function ($qw) use($query){
                $qw->where('NAMA_SUPPLIER', 'like', '%'. $query .'%');
            })
            ->with('tokos')
            ->with('supplier')
            ->orderBy('TOKO')
            ->orderBy('UPC')
            ->limit($limit)
            ->offset($offset)
            ->get();

        return $this->fmt(true, 'Data Product', $data);
    }

    public function product($toko, $id){

	    $data = Stock::where('TOKO', $toko)
            ->where('UPC', $id)
            ->with('tokos')
            ->with('supplier')
            ->first();

        if (count($data) == 1) {

            return $this->fmt(true, 'Data Product', $data);
        }else{
            $data = [
                'toko'          => '',
                'upc'           => '',
                'nama_barang'   => '',
                'jumlah_stock'  => '',
                'jual'          => '',
                'tokos'         => [
                    'toko'      => '',
                    'nama_toko' => '',
                ],
                'supplier'      => []
            ];


            return $this->fmt(false, 'Kode product tidak ada atau salah', $data);
        }        
    }
}
