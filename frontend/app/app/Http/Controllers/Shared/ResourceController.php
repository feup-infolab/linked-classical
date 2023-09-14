<?php

namespace App\Http\Controllers\Shared;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class ResourceController extends Controller
{
  public function show($id)
  {
    $endpoint = env("SERVER_URL") . '/resource/' . $id;

    $response = Http::get($endpoint)->json();

    return view('web.resource.show', [
      'id' => $id,
      'resource' => $response,
    ]);
  }

  public function getExternalDatasets() {
    $endpoint = env("SERVER_URL") . '/resource/external-datasets';

    return Http::withHeaders([
      'Content-Type' => 'application/json'
    ])->get($endpoint);
  }

  public function linkExternalEntity(Request $request){
    $endpoint = env("SERVER_URL") . '/resource/link-external-entity';

    return Http::withHeaders([
      'Content-Type' => 'application/json'
    ])->post($endpoint, $request->all()
    );
  }

  public function unlinkExternalEntity(Request $request){
    $endpoint = env("SERVER_URL") . '/resource/unlink-external-entity';

    return Http::withHeaders([
      'Content-Type' => 'application/json'
    ])->delete($endpoint, $request->all()
    );
  }
}
