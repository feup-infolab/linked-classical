@extends('web.layout')

@section('title')
  <title> Linked Classical Resource </title>
@endsection

@section('scripts')
  <script src="{{ asset('js/resource.js') }}" defer></script>
@endsection

@section('main')

  <section class="mx-6 mx-auto py-6 px-2 xl:px-4 max-w-screen-lg">

      <div class="font-thin mb-4">
            <a href="{{ env('APP_URL') }}">Home</a>
            >
            <a href="{{ env('APP_URL') . '/search' }}">{{ $resource["type"] ?? "Resource" }}</a>
            >
            <span>{{ $resource["label"] ?? $resource["URI"]}}</span>
          </div>

    @isset($resource["URI"])
      <h1 class="text-3xl mb-5">
        @if(isset($resource["label"]))
          <div>{{ $resource["label"] }}</div>
          <div class="italic flex items-center text-lg">
                    <span id='uri' class="mr-4">
                      {{ $resource["URI"] }}
                    </span>
            <button id="copy-uri" class="text-nsblack hover:text-gray tooltip">
              @svg('heroicon-o-document-duplicate', 'h-6 w-6')
              <span id="tooltip-uri" class="tooltiptext text-sm">
                          Copied!
                      </span>
            </button>
          </div>
        @else
          {{ $resource["URI"] }}
        @endif
      </h1>

      <h2 class="text-2xl">Outgoing relations</h2>

      <div class="my-4 flex">
        <div class="w-full bg-white">
          <table class="table table-bordered table-striped rounded-lg text-sm">
            <tr>
              <th>Property</th>
              <th>Value</th>
            </tr>

            @forelse($resource["outgoingStatements"] as $outgoingStatement)

              <tr>
                <td class="predicate">
                  <a href="{{$outgoingStatement["predicate"]}}" target="_blank" class="underline">
                    {{ $outgoingStatement["predicate"] }}
                  </a>
                </td>
                <td>
                  @isset($outgoingStatement["objectUuid"])
                    <a href="{{ env('APP_URL') . '/resource/' . $outgoingStatement['objectUuid'] }}" class="underline">
                      {{ $outgoingStatement["object"] }}
                    </a>
                  @else
                    @if(substr( $outgoingStatement["object"], 0, 4 ) === "http")
                      <a href='{{ $outgoingStatement["object"] }}' target="_blank" class="underline">
                        {{ $outgoingStatement["object"] }}
                      </a>
                    @else
                      {{ $outgoingStatement["object"] }}
                    @endif
                  @endif
                </td>
              </tr>

            @empty
              <tr>
                <td class="italic text-gray-dark text-center" colspan="2">No Information</td>
              </tr>
            @endforelse
          </table>
        </div>
      </div>


      <h2 class="text-2xl mt-12">Incoming relations</h2>

      <div class="my-4 flex">
        <div class="w-full bg-white">
          <table class="table table-bordered table-striped rounded-lg text-sm">
            <tr>
              <th>Property</th>
              <th>Value</th>
            </tr>

            @forelse($resource["incomingStatements"] as $incomingStatements)

              <tr>
                <td class="predicate">
                  <a href="{{$incomingStatements["predicate"]}}" target="_blank" class="underline">
                    {{ $incomingStatements["predicate"] }}
                  </a>
                </td>
                <td>
                  @isset($incomingStatements["subjectUuid"])
                    <a href="{{ env('APP_URL') . '/resource/' . $incomingStatements['subjectUuid'] }}" class="underline">
                      {{ $incomingStatements["subject"] }}
                    </a>
                  @else
                    @if(substr( $incomingStatements["subject"], 0, 4 ) === "http")
                      <a href='{{ $incomingStatements["subject"] }}' target="_blank" class="underline">
                        {{ $incomingStatements["subject"] }}
                      </a>
                    @else
                      {{ $incomingStatements["subject"] }}
                    @endif
                  @endif
                </td>
              </tr>

            @empty
              <tr>
                <td class="italic text-gray-dark text-center" colspan="2">No Information</td>
              </tr>
            @endforelse
          </table>
        </div>
      </div>



    @else
      <span class="mx-3">Resource not found</span>
    @endisset
  </section>

@endsection


