<?php

namespace App\Http\Controllers\Shared;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class SearchController extends Controller
{
  /**
   * Show the profile for a given document.
   *
   * @param  int  $id
   * @return \Illuminate\View\View
   */
  public function home(Request $request, $dataset = null)
  {
    $docs_endpoint = env("SERVER_URL");
    $entities_endpoint = env("SERVER_URL");

    // TODO: Add validation of $dataset
    if ($dataset != null) {
      $docs_endpoint = $docs_endpoint . '/' . $dataset . '/docs';
      $entities_endpoint = $entities_endpoint . '/' . $dataset . '/entities';
    } else {
      $docs_endpoint = $docs_endpoint . '/docs';
      $entities_endpoint = $entities_endpoint . '/entities';
    }

    $response = Http::get($docs_endpoint, []);
    $responseE = Http::get($entities_endpoint, []);

    return view('web.home.home', [
      'results' => $response->json(),
      'resultsE' => $responseE->json(),
      'dataset' => $dataset
    ]);
  }
}
