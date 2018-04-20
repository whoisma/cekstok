<?php

namespace App;

use Yajra\Oci8\Eloquent\OracleEloquent as Eloquent;

class User extends Eloquent{

    protected $binaries = ['content'];
}
