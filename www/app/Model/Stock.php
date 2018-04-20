<?php

namespace App\Model;

use Yajra\Oci8\Eloquent\OracleEloquent as Eloquent;

class Stock extends Eloquent{

    protected $table = "DATA_STOCK";

    public function tokos(){

    	return $this->hasOne("App\Model\Toko", 'toko', 'toko');
    }

    public function supplier(){
    	
    	return $this->hasMany("App\Model\Supplier", 'upc', 'upc');
    }
}
