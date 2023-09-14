<?php

namespace App\Http\Controllers\Shared;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class SearchController extends Controller
{
  public function search(Request $request)
  {
    $searchText = $request->query('q', "");

    $composersEndpoint = env("SERVER_URL") . '/composer/search/' . $searchText;
    $composers = Http::get($composersEndpoint)->json();

    $musicalWorksEndpoint = env("SERVER_URL") . '/musicalWork/search/' . $searchText;
    $musicalWorks = Http::get($musicalWorksEndpoint)->json();

    return view('web.search.show', [
      'query' => $searchText,
      'composers' => $composers ?? [],
      'musicalWorks' => $musicalWorks ?? [],
    ]);
  }
}
