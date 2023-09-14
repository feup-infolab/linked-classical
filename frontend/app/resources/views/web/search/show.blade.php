@extends('web.layout')

@section('title')
<title>Search "{{$query}}" - Linked Classical</title>
@endsection

@section('scripts')
<script src="{{ asset('js/search.js') }}" defer></script>
@endsection

@section('main')
  <section id="search-box" class="mx-6 mx-auto py-6 px-2 xl:px-4 max-w-screen-lg">
    <!--Search Bar-->
    <form autocomplete="off" class="w-full mb-5" action="{{ env('APP_URL') }}/search" method="get">
      <div class="relative mx-2">
        <input id="searchInput" name="q" class="px-1 py-2 hover:text-gray-dark w-full border-b text-xl" value="{{$query}}" type="text" placeholder="Search for composers, conductors, musical works and more">
        <button class="absolute top-2 right-2 hover:text-gray-dark" type="submit">
          @svg('heroicon-s-search', 'w-7 h-7')
        </button>
      </div>
    </form>
    <div class="mx-2 text-lg">
      {{ count($composers) == 100 ? "100+" : count($composers) }} composers and {{ count($musicalWorks) == 100 ? "100+" : count($musicalWorks) }} musical works in search for "{{$query}}"
    </div>

  </section>

  <section id="results" class="mx-auto max-w-screen-lg">
    <article class="mx-6 mb-6">
      <ul class="pb-1 border-b border-gray-dark text-base text-gray-dark flex flex-row justify-start">
        <li class="nav-buttons mr-5 p-2 rounded bg-gray-dark text-white" toggle="composers">
          <button id="composers-tab-button">Composers ({{ count($composers) == 100 ? "100+" : count($composers) }})</button>
        </li>
        <li class="nav-buttons mr-5 p-2 px-6 rounded hover:bg-gray-light hover:text-black" toggle="works">
          <button id="entity-tab-button">Musical Works ({{ count($musicalWorks) == 100 ? "100+" : count($musicalWorks) }})</button>
        </li>
      </ul>

      <ul class="tab px-1" toggle="composers">
        @forelse ($composers as $composer)
          <li class="my-6 rounded bg-gray-light">
            <div class="py-4 px-6 w-11/12">
              <div class="pt-1 pb-1 text-lg truncate">
                <span class="underline">
                  <a href="{{ env('APP_URL') . '/composer/' . $composer["id"] }}" class="underline">
                    <div>{{ $composer["label"] }}</div>
                  </a>
                </span>
              </div>
              <div class="pt-1 italic">
                <a href="{{ env('APP_URL') . '/composer/' . $composer["id"] }}">
                  <div>{{ $composer["resource"] }}</div>
                </a>
              </div>
            </div>
          </li>
        @empty
          <li class="my-8 text-lg text-gray italic text-center">
            No results
          </li>
        @endforelse
      </ul>

      <ul class="tab px-1 hidden" toggle="works">
        @forelse ($musicalWorks as $musicalWork)
          <li class="my-6 rounded bg-gray-light">
            <div class="py-4 px-6 w-11/12">
              <div class="pt-1 pb-1 text-lg truncate">
                <span class="underline">
                  <a href="{{ env('APP_URL') . '/musicalWork/' . $musicalWork["id"] }}" class="underline">
                    <div>{{ $musicalWork["label"] }}</div>
                  </a>
                </span>
              </div>
              <div class="pt-1 italic">
                <a href="{{ env('APP_URL') . '/musicalWork/' . $musicalWork["id"] }}">
                  <div>{{ $musicalWork["resource"] }}</div>
                </a>
              </div>
              <div class="pt-1">
                <span>Composed by </span>
                <a href="{{ env('APP_URL') . '/composer/' . $musicalWork["composerId"] }}">
                  <span class="underline">{{ $musicalWork["composerLabel"] }}</span>
                </a>
                </a>
              </div>
            </div>
          </li>
        @empty
          <li class="my-8 text-lg text-gray italic text-center">
            No results
          </li>
        @endforelse
      </ul>
    </article>
  </section>

@endsection
