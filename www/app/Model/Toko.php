<?php

namespace App\Model;

use Yajra\Oci8\Eloquent\OracleEloquent as Eloquent;

class Toko extends Eloquent{

    protected $table = "DATA_TOKO";

    protected $primaryKey = 'TOKO';
}
