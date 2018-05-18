<?php
/**
 * Created by PhpStorm.
 * User: kaymo
 * Date: 17/05/2018
 * Time: 13:10
 */

namespace App\Http\Controllers;


use App\Reclamation;
use Illuminate\Http\Request;


class ReclamationController extends Controller
{
    public function showAll()
    {
        return response()->json(Reclamation::all());
    }

    public function show($id)
    {
        return response()->json(Reclamation::find($id));
    }

    public function create(Request $request)
    {
        $dado = Reclamation::create($request->all());

        return response()->json($dado, 201);
    }

    public function update(Request $request, $id)
    {
        $reclamation = Reclamation::findOrFail($id);
        $reclamation->update($request->all());
        return response()->json($reclamation, 200);
    }

    public function delete($id)
    {
        Reclamation::findOrfail($id)->delete();
        return response('Registro excluído com sucesso', 200);
    }
}