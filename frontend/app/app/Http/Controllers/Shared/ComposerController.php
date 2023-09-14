<?php

namespace App\Http\Controllers\Shared;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class ComposerController extends Controller
{
  public function show($id)
  {
    $endpoint = env("SERVER_URL") . '/composer/' . $id;

    $response = Http::get($endpoint)->json();

    $externalInfoboxesInfoEndpoint = env("SERVER_URL") . '/resource/external-infoboxes';

    $externalInfoboxesInfo = Http::get($externalInfoboxesInfoEndpoint, [
      'uuid' => $id,
    ]);

    $externalInfoSummaryEndpoint = env("SERVER_URL") . '/resource/external-info-summary';

    $externalInfoSummary = Http::get($externalInfoSummaryEndpoint, [
      'uuid' => $id,
    ]);

    return view('web.composer.show', [
      'id' => $id,
      'composer' => $response,
      'externalInfoboxesInfo' => $externalInfoboxesInfo,
      'externalInfoSummary' => $externalInfoSummary,
    ]);
  }

  public function create()
  {
    return view('web.composer.create');
  }

  public function store(Request $request)
  {
    $endpoint = env("SERVER_URL") . '/composer/';

    return Http::withHeaders([
      'Content-Type' => 'application/json'
    ])->post($endpoint, $request->all());
  }

}
