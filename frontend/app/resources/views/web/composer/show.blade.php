@extends('web.layout')

@section('title')
  <title> Composer </title>
@endsection

@section('scripts')
  <script src="{{ asset('js/composer.js') }}" defer></script>
  <script src="{{ asset('js/externalData.js') }}" defer></script>
@endsection

@section('main')
  <section class="mx-6 mx-auto py-6 px-2 xl:px-4 max-w-screen-lg">
    <div class="border-l border-gray"></div>
    <div class="min-w-100">
      <div id="document-header" class="flex flex-row items-start justify-between">
        
        <div id="document-identity">
          <div class="font-thin mb-4">
            <a href="{{ env('APP_URL') }}">Home</a>
            >
            <a href="{{ env('APP_URL') . '/search' }}">Composer</a>
            >
            <span>{{ $composer["name"] ?? "Composer Not Found"}}</span>
          </div>
          <!--Title-->
            <div class="mb-1 text-3xl flex-1">
              {{ $composer["name"] ?? "Composer Not Found"}}
            </div>

          <div class="flex items-center margin-auto">

          <div class="italic flex items-center text-lg mr-12 pr-2">
                    <span id='uri' class="mr-4">
                      {{ $composer["URI"] ?? "Composer Not Found" }}
                    </span>
            <button id="copy-uri" class="text-nsblack hover:text-gray tooltip">
              @svg('heroicon-o-document-duplicate', 'h-6 w-6')
              <span id="tooltip-uri" class="tooltiptext text-sm">
                          Copied!
                      </span>
            </button>
          </div>

          <div class="ml-24">
              <a class="mr-5 p-2 rounded border-gray-dark border-2" href="{{ env('APP_URL') . '/resource/' . $composer["id"] }}">
                <button class="font-thin italic">View raw data</button>
              </a>
            </div>
          </div>


        </div>
      </div>

      <article class="mt-5 mb-6 max-w-screen-lg">

        <ul class="pb-1 mb-4 w-100 border-b border-gray-dark text-base text-gray-dark flex flex-row justify-start">
          <li class="nav-buttons mr-5 p-2 rounded bg-gray-dark text-white" toggle="description">
            <button>Description</button>
          </li>
          <li class="nav-buttons mr-5 p-2 px-6 rounded hover:bg-gray-light hover:text-black" toggle="external-data">
            <button id="external-data">External Data</button>
        </ul>

        <ul class="tab" toggle="description">
          <ul id="key">
            @include('web.composer.components.show.fields.nationalities', ['nationalities' => $composer["nationalities"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="alias">
            @include('web.composer.components.show.fields.alias', ['aliasArray' => $composer["alias"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="period">
            @include('web.composer.components.show.fields.period', ['period' => $composer["period"] ?? null])
            <hr class="my-4">
          </ul>
          <ul id="birth">
            @include('web.composer.components.show.fields.birth', ['birth' => $composer["birth"] ?? null])
            <hr class="my-4">
          </ul>
          <ul id="death">
            @include('web.composer.components.show.fields.death', ['death' => $composer["death"] ?? null])
            <hr class="my-4">
          </ul>
          <ul id="locations">
            @include('web.composer.components.show.fields.locations', ['locations' => $composer["locations"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="wikipediaPage">
            @include('web.composer.components.show.fields.wikipediaPage', ['wikipediaPage' => $composer["wikipediaPage"] ?? null])
            <hr class="my-4">
          </ul>
          <ul id="pages">
            @include('web.composer.components.show.fields.pages', ['pages' => $composer["pages"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="hasInfluenced">
            @include('web.composer.components.show.fields.hasInfluenced', ['hasInfluencedArray' => $composer["hasInfluenced"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="influencedBy">
            @include('web.composer.components.show.fields.influencedBy', ['influencedByArray' => $composer["influencedBy"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="stylePeriods">
            @include('web.composer.components.show.fields.stylePeriods', ['stylePeriods' => $composer["stylePeriods"] ?? []])
            <hr class="my-4">
          </ul>
          <ul id="sameAs">
            @include('web.composer.components.show.fields.sameAs', ['sameAsArray' => $composer["sameAs"] ?? []])
            <hr class="my-4">
          </ul>
        </ul>

        <ul class="tab hidden" toggle="external-data">
          @include('web.composer.components.show.external_data', ['links' => $composer["sameAs"] ?? [], 'externalInfoboxesInfo' => $externalInfoboxesInfo, 'externalInfoSummary' => $externalInfoSummary])
        </ul>
      </article>

    </div>
  </section>

@endsection
