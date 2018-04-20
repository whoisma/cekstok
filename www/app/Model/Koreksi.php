<?php

namespace App\Model;

use Yajra\Oci8\Eloquent\OracleEloquent as Eloquent;

class Koreksi extends Eloquent{

    protected $table = "DATA_KOREKSI";

    public $timestamps = false;

    protected $sequence = null;

    public $incrementing = false;

    protected $dates = ['TGL_INPUT'];
}
