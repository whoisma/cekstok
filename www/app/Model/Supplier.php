<?php

namespace App\Model;

use Yajra\Oci8\Eloquent\OracleEloquent as Eloquent;

class Supplier extends Eloquent{

    protected $table = "DATA_SUPPLIER";

    protected $primaryKey = 'TOKO';
}
