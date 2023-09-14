<?php

namespace App\Http\Controllers\Shared;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class MusicalWorkController extends Controller
{
  public function show($id)
  {
    $endpoint = env("SERVER_URL") . '/musicalWork/' . $id;

    $response = Http::get($endpoint)->json();

    return view('web.musicalWork.show', [
      'id' => $id,
      'musicalWork' => $response
    ]);
  }

  public function create() {
    return view('web.musicalWork.create');
  }

  public function store(Request $request) {
    $endpoint = env("SERVER_URL") . '/musicalWork/';

    return Http::withHeaders([
      'Content-Type' => 'application/json'
    ])->post($endpoint, $request->all());
  }

}
