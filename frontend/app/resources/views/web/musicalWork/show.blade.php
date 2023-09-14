@extends('web.layout')

@section('title')
  <title> Musical Work </title>
@endsection

@section('scripts')
  <script src="{{ asset('js/musicalWork.js') }}" defer></script>
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
            <a href="{{ env('APP_URL') . '/search' }}">Musical Work</a>
            >
            <span>{{ $musicalWork["title"] ?? "Musical Work Not Found"}}</span>
          </div>
          <!--Title-->
          <div class="mb-1 w-100 text-3xl">
            {{ $musicalWork["title"] ?? "Musical Work Not Found" }}
          </div>

          <div class="flex items-center margin-auto">

          <div class="italic flex items-center text-lg mr-12 pr-2">
                    <span id='uri' class="mr-4">
                      {{ $musicalWork["URI"] ?? "Musical Work Not Found" }}
                    </span>
            <button id="copy-uri" class="text-nsblack hover:text-gray tooltip">
              @svg('heroicon-o-document-duplicate', 'h-6 w-6')
              <span id="tooltip-uri" class="tooltiptext text-sm">
                          Copied!
                      </span>
            </button>
          </div>

          <div class="ml-24">
              <a class="mr-5 p-2 rounded border-gray-dark border-2" href="{{ env('APP_URL') . '/resource/' . $musicalWork["id"] }}">
                <button class="font-thin italic">View raw data</button>
              </a>
            </div>
          </div>

        </div>
      </div>

      <article class="mt-5 mb-6 max-w-screen-lg">
        <hr class="my-4">
        <ul id="key">
          @include('web.musicalWork.components.show.fields.key', ['key' => $musicalWork["key"] ?? null])
          <hr class="my-4">
        </ul>
        <ul id="number">
          @include('web.musicalWork.components.show.fields.number', ['number' => $musicalWork["number"] ?? null])
          <hr class="my-4">
        </ul>
        <ul id="opus">
          @include('web.musicalWork.components.show.fields.opus', ['opus' => $musicalWork["opus"] ?? null])
          <hr class="my-4">
        </ul>
        <ul id="composer">
          @include('web.musicalWork.components.show.fields.composer', ['composerURI' => $musicalWork["composition"]["composerURI"] ?? null, 'composerUuid' => $musicalWork["composition"]["composerUuid"] ?? null])
          <hr class="my-4">
        </ul>
        <ul id="date">
          @include('web.musicalWork.components.show.fields.date', ['date' => $musicalWork["composition"]["date"] ?? null])
          <hr class="my-4">
        </ul>
        <ul id="isPartOf">
          @include('web.musicalWork.components.show.fields.isPartOf', ['isPartOf' => $musicalWork["isPartOf"]["musicalWorkURI"] ?? null, 'isPartOfUuid' => $musicalWork["isPartOf"]["musicalWorkUuid"] ?? null])
          <hr class="my-4">
        </ul>
        <ul id="documents">
          @include('web.musicalWork.components.show.fields.documents', ['documents' => $musicalWork["documents"] ?? []])
          <hr class="my-4">
        </ul>
        <ul id="documents">
          @include('web.musicalWork.components.show.fields.alias', ['aliasArray' => $musicalWork["alias"] ?? []])
          <hr class="my-4">
        </ul>
        <ul id="documents">
          @include('web.musicalWork.components.show.fields.remarks', ['remarks' => $musicalWork["remarks"] ?? null])
          <hr class="my-4">
        </ul>
      </article>

    </div>
  </section>
@endsection
